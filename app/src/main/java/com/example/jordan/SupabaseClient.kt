package com.example.jordan

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = "https://vopbzurfnxuvwuubeiip.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZvcGJ6dXJmbnh1dnd1dWJlaWlwIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzQyNDA2NjgsImV4cCI6MjA0OTgxNjY2OH0.EBisfjao1FU0voVqzk5r9n42fSShkUV90Ba13WMbPLw"
    ) {
        install(Auth)
        install(Postgrest)
    }
}