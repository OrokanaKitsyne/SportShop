package com.example.sportshop.ui.theme

import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://zeivknuxlnrgxqcrppnl.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InplaXZrbnV4bG5yZ3hxY3JwcG5sIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzI2MTM5OTMsImV4cCI6MjA4ODE4OTk5M30.-3IJrvYP3dER_0fpP_eO6S6NsobRyostSxIs4rFns4Q"
    ) {
        install(Postgrest)
        install(Auth)
        install(Storage)
    }
}