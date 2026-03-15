package com.example.sportshop.ui.di

<<<<<<< HEAD
import com.example.sportshop.ui.data.remote.AuthApi
import com.example.sportshop.ui.data.remote.AuthApiService
import com.example.sportshop.ui.data.remote.ProductApi
import com.example.sportshop.ui.data.remote.ProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
=======
@Module
@InstallIn(SingletonComponent::class)

>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
object NetworkModule {

    @Provides
    @Singleton
<<<<<<< HEAD
    fun provideRetrofit(): Retrofit {
        return AuthApiService.retrofit()
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): ProfileApi {
        return retrofit.create(ProfileApi::class.java)
=======
    fun provadesauthApi():AuthApi
    {
        return AuthApiService.create()
    }


    @Provides
    @Singleton
    fun provideProductsApi(): ProductsApi {
        return ProductsApiService.create()
>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
    }
}