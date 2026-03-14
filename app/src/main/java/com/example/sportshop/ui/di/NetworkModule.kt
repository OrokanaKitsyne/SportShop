package com.example.sportshop.ui.di

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {

    @Provides
    @Singleton
    fun provadesauthApi():AuthApi
    {
        return AuthApiService.create()
    }


    @Provides
    @Singleton
    fun provideProductsApi(): ProductsApi {
        return ProductsApiService.create()
    }
}