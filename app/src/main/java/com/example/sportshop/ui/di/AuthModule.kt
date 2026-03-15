package com.example.sportshop.ui.di
<<<<<<< HEAD

import com.example.sportshop.ui.data.remote.AuthApi
import com.example.sportshop.ui.data.remote.ProductApi
import com.example.sportshop.ui.data.remote.ProfileApi
import com.example.sportshop.ui.data.repository.AuthRepositoryIml
import com.example.sportshop.ui.data.repository.ProductRepositoryImpl
import com.example.sportshop.ui.data.repository.ProfileRepositoryImpl
import com.example.sportshop.ui.domain.repository.AuthRepository
import com.example.sportshop.ui.domain.repository.ProductRepository
import com.example.sportshop.ui.domain.repository.ProfileRepository
import com.example.sportshop.ui.domain.usecase.LoginUseCase
import com.example.sportshop.ui.domain.usecase.RegisterUseCase
=======
import com.example.sportshop.ui.data.remote.AuthApi
import com.example.exam3.data.remote.ProductsApi
import com.example.exam3.data.repository.AuthRepositoruIml
import com.example.exam3.data.repository.ProductsRepositoryImpl
import com.example.exam3.domain.repository.AuthRepository
import com.example.exam3.domain.repository.ProductsRepository
import com.example.exam3.domain.usecase.DeleteProductUseCase
import com.example.exam3.domain.usecase.GetProductsUseCase
import com.example.exam3.domain.usecase.LoginUseCase
import com.example.exam3.domain.usecase.UpdateProductUseCase
>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

<<<<<<< HEAD
@Module
=======

@Module

>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
<<<<<<< HEAD
    fun provideAuthRepository(authApi: AuthApi): AuthRepository {
        return AuthRepositoryIml(authApi)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(authRepository: AuthRepository): LoginUseCase {
        return LoginUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(authRepository: AuthRepository): RegisterUseCase {
        return RegisterUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideProductRepository(productApi: ProductApi): ProductRepository {
        return ProductRepositoryImpl(productApi)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(profileApi: ProfileApi): ProfileRepository {
        return ProfileRepositoryImpl(profileApi)
=======
    fun provideAuthRepository(authApi: AuthApi):AuthRepository
    {
        return AuthRepositoruIml(authApi)
    }
    @Provides
    @Singleton
    fun provideProductsRepository(productsApi: ProductsApi): ProductsRepository {
        return ProductsRepositoryImpl(productsApi)
    }
    @Provides
    @Singleton
    fun provideAuthLoginUseCase(authRepository: AuthRepository):LoginUseCase
    {
        return LoginUseCase(authRepository)
    }


    @Provides
    @Singleton
    fun provideGetProductsUseCase(repository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCase(repository)
    }


    // AuthModule.kt (добавьте)
    @Provides
    @Singleton
    fun provideUpdateProductUseCase(repository: ProductsRepository): UpdateProductUseCase {
        return UpdateProductUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideDeleteProductUseCase(repository: ProductsRepository): DeleteProductUseCase {
        return DeleteProductUseCase(repository)
>>>>>>> df8b2d2de87eee3e940c12664ce805d2453ecd19
    }
}