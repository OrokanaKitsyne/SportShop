package com.example.sportshop.ui.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.Tune
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sportshop.R
import com.example.sportshop.ui.data.repository.AuthRepositoryIml
import com.example.sportshop.ui.domain.model.ProductItem
import com.example.sportshop.ui.presentation.components.ProfileDrawerContent
import com.example.sportshop.ui.presentation.viewmodel.HomeViewModel
import com.example.sportshop.ui.presentation.viewmodel.MenuViewModel
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

private val PrimaryBlue = Color(0xFF4DB7F2)
private val DrawerBlue = Color(0xFF52B6EA)
private val ScreenBg = Color(0xFFF5F5F7)
private val SoftGray = Color(0xFFEDEDEF)
private val TextDark = Color(0xFF2C2C2C)
private val TextLight = Color(0xFF9CA3AF)
private val SalePurple = Color(0xFF6C4DFF)

@Composable
fun ProductsScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    menuViewModel: MenuViewModel = hiltViewModel()
) {
    val state by viewModel.uiState
    val menuState by menuViewModel.uiState

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val filteredProducts = if (state.selectedCategory == "Все") {
        state.products
    } else {
        state.products.filter { it.categoryTitle == state.selectedCategory }
    }

    val bestSellerProducts = filteredProducts.filter { it.isBestSeller }.ifEmpty { filteredProducts }
    val popularProducts = filteredProducts.take(8)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = DrawerBlue,
                drawerContentColor = Color.White
            ) {
                ProfileDrawerContent(
                    fullName = menuState.profile?.fullName ?: "Пользователь",
                    photoUrl = menuState.profile?.photoUrl,
                    onProfileClick = {
                        scope.launch { drawerState.close() }
                        navController.navigate(AppRoutes.PROFILE)
                    },
                    onCartClick = {
                        scope.launch { drawerState.close() }
                    },
                    onFavoritesClick = {
                        scope.launch { drawerState.close() }
                    },
                    onOrdersClick = {
                        scope.launch { drawerState.close() }
                    },
                    onNotificationsClick = {
                        scope.launch { drawerState.close() }
                    },
                    onSettingsClick = {
                        scope.launch { drawerState.close() }
                    },
                    onLogoutClick = {
                        AuthRepositoryIml.currentToken = null
                        AuthRepositoryIml.currentUserId = null
                        navController.navigate(AppRoutes.LOGIN) {
                            popUpTo(0)
                        }
                    }
                )
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = ScreenBg
        ) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PrimaryBlue)
                    }
                }

                state.error != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.error ?: "Ошибка загрузки",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Red
                        )
                    }
                }

                else -> {
                    Scaffold(
                        containerColor = ScreenBg,
                        bottomBar = {
                            BottomBar(navController = navController)
                        }
                    ) { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState())
                                .padding(innerPadding)
                                .padding(top = 18.dp)
                        ) {
                            HomeTopBar(
                                onMenuClick = {
                                    scope.launch { drawerState.open() }
                                }
                            )

                            Spacer(modifier = Modifier.height(18.dp))
                            SearchSection()
                            Spacer(modifier = Modifier.height(20.dp))

                            SectionTitle(title = "Категории")
                            Spacer(modifier = Modifier.height(12.dp))

                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                contentPadding = PaddingValues(horizontal = 20.dp)
                            ) {
                                items(state.categories) { category ->
                                    CategoryChip(
                                        title = category.title,
                                        selected = category.title == state.selectedCategory,
                                        onClick = { viewModel.selectCategory(category.title) }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(22.dp))
                            SectionTitle(title = "Популярное", actionText = "Все")
                            Spacer(modifier = Modifier.height(12.dp))

                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(14.dp),
                                contentPadding = PaddingValues(horizontal = 20.dp)
                            ) {
                                items(popularProducts) { product ->
                                    ProductCard(product = product)
                                }
                            }

                            Spacer(modifier = Modifier.height(22.dp))
                            SectionTitle(title = "Акции", actionText = "Все")
                            Spacer(modifier = Modifier.height(12.dp))

                            PromoCard()

                            Spacer(modifier = Modifier.height(22.dp))
                            SectionTitle(title = "Лучшее", actionText = "Все")
                            Spacer(modifier = Modifier.height(12.dp))

                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(14.dp),
                                contentPadding = PaddingValues(horizontal = 20.dp)
                            ) {
                                items(bestSellerProducts) { product ->
                                    ProductCard(product = product)
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeTopBar(
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onMenuClick) {
            Icon(
                imageVector = Icons.Outlined.Menu,
                contentDescription = "Меню",
                tint = TextDark
            )
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Главная",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Medium,
                color = TextDark
            )
        }

        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingBag,
                contentDescription = "Корзина",
                tint = TextDark
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 6.dp, end = 6.dp)
                    .size(7.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFF6B57))
            )
        }
    }
}

@Composable
private fun SearchSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = "",
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            placeholder = {
                Text("Поиск", color = TextLight)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = TextLight
                )
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                focusedTextColor = TextDark,
                unfocusedTextColor = TextDark
            )
        )

        Spacer(modifier = Modifier.width(12.dp))

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(PrimaryBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Tune,
                contentDescription = "Фильтры",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun SectionTitle(
    title: String,
    actionText: String? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium,
            color = TextDark,
            modifier = Modifier.weight(1f)
        )

        if (actionText != null) {
            Text(
                text = actionText,
                color = PrimaryBlue,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
private fun CategoryChip(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) PrimaryBlue else Color.White)
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = if (selected) Color.White else TextDark,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ProductCard(product: ProductItem) {
    Card(
        modifier = Modifier
            .width(155.dp)
            .height(235.dp),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(SoftGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Избранное",
                        tint = TextDark,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF3F8FF)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.boots),
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(horizontal = 8.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = if (product.isBestSeller) "BEST SELLER" else product.categoryTitle.uppercase(),
                color = PrimaryBlue,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Medium,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = product.title,
                color = TextDark,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = formatPrice(product.cost),
                    color = TextDark,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f)
                )

                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(
                            RoundedCornerShape(
                                topStart = 12.dp,
                                topEnd = 12.dp,
                                bottomStart = 12.dp
                            )
                        )
                        .background(PrimaryBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Добавить",
                        tint = Color.White,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}

private fun formatPrice(price: Double): String {
    val symbols = DecimalFormatSymbols(Locale("ru")).apply {
        groupingSeparator = ' '
    }
    val formatter = DecimalFormat("#,###", symbols)
    return "₽${formatter.format(price)}"
}

@Composable
private fun PromoCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Summer Sale",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextDark
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "15% OFF",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = SalePurple
                )
            }

            Box(
                modifier = Modifier
                    .size(width = 100.dp, height = 70.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF7F3FF)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.sale),
                    contentDescription = "Акция",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 6.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}

@Composable
private fun BottomBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .navigationBarsPadding()
            .padding(horizontal = 24.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarIcon(
            icon = Icons.Outlined.Home,
            selected = true
        )

        BottomBarIcon(
            icon = Icons.Outlined.FavoriteBorder,
            selected = false
        )

        Box(
            modifier = Modifier
                .size(58.dp)
                .clip(CircleShape)
                .background(PrimaryBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingBag,
                contentDescription = null,
                tint = Color.White
            )
        }

        BottomBarIcon(
            icon = Icons.Outlined.NotificationsNone,
            selected = false
        )

        BottomBarIcon(
            icon = Icons.Outlined.PersonOutline,
            selected = false,
            onClick = {
                navController.navigate(AppRoutes.PROFILE)
            }
        )
    }
}

@Composable
private fun BottomBarIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: (() -> Unit)? = null
) {
    Icon(
        imageVector = icon,
        contentDescription = null,
        tint = if (selected) PrimaryBlue else TextLight,
        modifier = Modifier
            .size(22.dp)
            .clickable(enabled = onClick != null) {
                onClick?.invoke()
            }
    )
}