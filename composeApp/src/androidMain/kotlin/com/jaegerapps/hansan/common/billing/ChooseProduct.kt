package com.jaegerapps.hansan.common.billing

import android.app.Activity
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ConsumeParams
import com.android.billingclient.api.ConsumeResponseListener
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.google.common.collect.ImmutableList
import com.jaegerapps.hansan.common.util.Knower
import com.jaegerapps.hansan.common.util.Knower.d
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ChooseProduct(
    private val activity: Activity
) {
    private val _purchases = MutableStateFlow<List<String>>(emptyList())
    val purchases = _purchases.asStateFlow()


    private val purchaseUpdateListener = PurchasesUpdatedListener { result, purchases ->
        if (result.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        } else if (result.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            // User canceled the purchase
        } else {
            // Handle other error cases
        }
    }
    private var billingClient: BillingClient = BillingClient.newBuilder(activity)
        .setListener(purchaseUpdateListener)
        .enablePendingPurchases()
        .build()

    fun billingSetup() {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(result: BillingResult) {
                if (result.responseCode == BillingClient.BillingResponseCode.OK) {
                    // Connected
                }
            }

            override fun onBillingServiceDisconnected() {
                // Handle billing service disconnection
            }
        })
    }

    private fun handlePurchase(purchase: Purchase) {

        // Consume the response
        val consumeParams = ConsumeParams.newBuilder()
            .setPurchaseToken(purchase.purchaseToken)
            .build()

        val listener = ConsumeResponseListener { billingResult, s -> }

        billingClient.consumeAsync(consumeParams, listener)

        // Making sure that the product is purchased
        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
            if (!purchase.isAcknowledged) {
                val acknowledgePurchaseParams = AcknowledgePurchaseParams
                    .newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                    .build()

                // Acknowledges the purchase
                billingClient.acknowledgePurchase(acknowledgePurchaseParams) { billingResult ->
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        // Adding the item to the list
                        _purchases.update {
                            val newList = it.toMutableList()
                            newList.add(purchase.products[0].toString())
                            newList
                        }
                    }
                }
            }
        }

    }
    fun purchase(
        productId: String,
    ) {
        // All products list
        val queryProductDetailsParams =
            QueryProductDetailsParams.newBuilder()
                .setProductList(
                    ImmutableList.of(
                        QueryProductDetailsParams.Product.newBuilder()
                            .setProductId("hansan_pro_version")
                            .setProductType(BillingClient.ProductType.INAPP)
                            .build()
                    )
                )
                .build()
        Knower.d("purchase", "Here is the query ${queryProductDetailsParams.zza()}")

        billingClient.queryProductDetailsAsync(queryProductDetailsParams) { billingResult, productDetailsList ->
            Knower.d("purchase", "Here is the billingResult $billingResult")

            when (billingResult.responseCode) {

            }
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                // Checking if the current product is the one that we want to buy
                Knower.d("purchase", "Here is the productDetailsList $productDetailsList")
                Knower.d("purchase", "Here is the productId ${productId}")

                val productDetails = productDetailsList.firstOrNull { productDetails ->
                    productDetails.productId == productId
                }
                productDetails?.let {
                    val productDetailsParamsList = listOf(
                        BillingFlowParams.ProductDetailsParams.newBuilder()
                            .setProductDetails(it)
                            .build()
                    )

                    val billingFlowParams = BillingFlowParams.newBuilder()
                        .setProductDetailsParamsList(productDetailsParamsList)
                        .build()

                    // Showing the purchase dialog
                    billingClient.launchBillingFlow(activity, billingFlowParams)
                }
            } else {
                Knower.d("purchase", "Here is the ResponseCode ${billingResult.responseCode}")
                Knower.d("purchase", "Here is the ResponseCode ${billingResult.debugMessage}")

            }
        }
    }
    fun checkProducts() {
        val queryPurchaseParams = QueryPurchasesParams.newBuilder()
            .setProductType(BillingClient.ProductType.INAPP)
            .build()

        billingClient.queryPurchasesAsync(
            queryPurchaseParams
        ) { result, purchases ->
            Knower.d("checkProducts", "Something is happening here?")

            when (result.responseCode) {
                BillingClient.BillingResponseCode.OK -> {
                    Knower.d("checkProducts", "Something is happening here?")
                    for (purchase in purchases) {
                        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                            // User has an active product
                            _purchases.update {
                                val newList = it.toMutableList()
                                newList.add(purchase.products[0].toString())
                                newList
                            }

                            return@queryPurchasesAsync
                        }
                    }
                }

                BillingClient.BillingResponseCode.USER_CANCELED -> {
                    Knower.d("checkProducts", "Something is happening here 2?")

                    // User canceled the purchase
                }

                else -> {
                    Knower.d("checkProducts", "Here is the else statement and the BillingClientResponse ${result.responseCode} \n ${result.debugMessage}")

                    // Handle other error cases
                }
            }

            // User does not have an active subscription
        }
    }
}