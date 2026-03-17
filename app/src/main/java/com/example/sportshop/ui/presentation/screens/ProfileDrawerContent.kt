package com.example.sportshop.ui.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.sportshop.R

private val DrawerBlue = Color(0xFF52B6EA)

@Composable
fun ProfileDrawerContent(
    fullName: String,
    photoUrl: String?,
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit,
    onFavoritesClick: () -> Unit,
    onOrdersClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.86f)
            .background(DrawerBlue)
            .padding(horizontal = 20.dp, vertical = 28.dp)
    ) {
        ProfileAvatar(photoUrl = photoUrl)

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = fullName,
            color = Color.White,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(28.dp))

        DrawerMenuItem(
            icon = Icons.Outlined.PersonOutline,
            title = "Профиль",
            onClick = onProfileClick
        )

        DrawerMenuItem(
            icon = Icons.Outlined.ShoppingBag,
            title = "Корзина",
            onClick = onCartClick
        )

        DrawerMenuItem(
            icon = Icons.Outlined.FavoriteBorder,
            title = "Избранное",
            onClick = onFavoritesClick
        )

        DrawerMenuItem(
            icon = Icons.Outlined.LocalShipping,
            title = "Заказы",
            onClick = onOrdersClick
        )

        DrawerMenuItem(
            icon = Icons.Outlined.NotificationsNone,
            title = "Уведомления",
            onClick = onNotificationsClick
        )

        DrawerMenuItem(
            icon = Icons.Outlined.Settings,
            title = "Настройки",
            onClick = onSettingsClick
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onLogoutClick)
                .padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ExitToApp,
                contentDescription = "Выйти",
                tint = Color.White
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "Выйти",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun ProfileAvatar(photoUrl: String?) {
    val hasPhoto = !photoUrl.isNullOrBlank()

    if (hasPhoto) {
        AsyncImage(
            model = photoUrl,
            contentDescription = "Фото профиля",
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.images),
            error = painterResource(id = R.drawable.images)
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.images),
            contentDescription = "Фото профиля",
            modifier = Modifier
                .size(76.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun DrawerMenuItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = Color.White
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.White
        )
    }
}