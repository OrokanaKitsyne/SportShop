package com.example.sportshop.ui.di

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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
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
    }
}