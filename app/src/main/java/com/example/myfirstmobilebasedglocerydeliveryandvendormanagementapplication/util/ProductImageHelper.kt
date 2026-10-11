package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.util

object ProductImageHelper {

    fun getRealImageUrl(productName: String, category: String = "", providedUrl: String = ""): String {
        val trimmed = providedUrl.trim()
        if (trimmed.startsWith("http://") || trimmed.startsWith("https://")) {
            return trimmed
        }

        val nameLower = productName.lowercase()
        val catLower = category.lowercase()

        return when {
            // Specific Produce & Grocery items
            nameLower.contains("tomato") ->
                "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?w=600&q=80"
            nameLower.contains("onion") ->
                "https://images.unsplash.com/photo-1618512496248-a07fe83aa8cf?w=600&q=80"
            nameLower.contains("matooke") || nameLower.contains("plantain") || nameLower.contains("banana") ->
                "https://images.unsplash.com/photo-1571771894821-ce9b6c11b08e?w=600&q=80"
            nameLower.contains("potato") || nameLower.contains("irish") ->
                "https://images.unsplash.com/photo-1518977676601-b53f82aba655?w=600&q=80"
            nameLower.contains("cabbage") ->
                "https://images.unsplash.com/photo-1594282486552-05b4d80fbb9f?w=600&q=80"
            nameLower.contains("sukuma") || nameLower.contains("spinach") || nameLower.contains("kale") || nameLower.contains("greens") ->
                "https://images.unsplash.com/photo-1628773822503-930a85818988?w=600&q=80"
            nameLower.contains("avocado") ->
                "https://images.unsplash.com/photo-1523049673857-eb18f1d7b578?w=600&q=80"
            nameLower.contains("mango") ->
                "https://images.unsplash.com/photo-1553279768-865429fa0078?w=600&q=80"
            nameLower.contains("orange") || nameLower.contains("citrus") ->
                "https://images.unsplash.com/photo-1547514701-42782101795e?w=600&q=80"
            nameLower.contains("pineapple") ->
                "https://images.unsplash.com/photo-1550258987-190a2d41a8ba?w=600&q=80"
            nameLower.contains("watermelon") ->
                "https://images.unsplash.com/photo-1587049352846-4a222e784d38?w=600&q=80"
            nameLower.contains("apple") ->
                "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?w=600&q=80"
            nameLower.contains("carrot") ->
                "https://images.unsplash.com/photo-1598170845058-32b9d6a5da37?w=600&q=80"
            nameLower.contains("pepper") || nameLower.contains("chili") || nameLower.contains("hoho") ->
                "https://images.unsplash.com/photo-1588252303782-cb80119abd6d?w=600&q=80"
            nameLower.contains("garlic") ->
                "https://images.unsplash.com/photo-1540148426945-6cf22a6b2383?w=600&q=80"
            nameLower.contains("ginger") ->
                "https://images.unsplash.com/photo-1615485290382-441e4d049cb5?w=600&q=80"
            nameLower.contains("bean") || nameLower.contains("pulse") || nameLower.contains("pea") ->
                "https://images.unsplash.com/photo-1551462147-ff29053bfc14?w=600&q=80"
            nameLower.contains("rice") ->
                "https://images.unsplash.com/photo-1586201375761-83865001e31c?w=600&q=80"
            nameLower.contains("posho") || nameLower.contains("maize") || nameLower.contains("flour") ->
                "https://images.unsplash.com/photo-1574323347407-f5e1ad6d020b?w=600&q=80"
            nameLower.contains("milk") || nameLower.contains("dairy") || nameLower.contains("yoghurt") || nameLower.contains("cheese") ->
                "https://images.unsplash.com/photo-1563636619-e9143da7973b?w=600&q=80"
            nameLower.contains("egg") ->
                "https://images.unsplash.com/photo-1582722872445-44dc5f7e3c8f?w=600&q=80"
            nameLower.contains("meat") || nameLower.contains("beef") || nameLower.contains("pork") || nameLower.contains("mutton") ->
                "https://images.unsplash.com/photo-1603048588665-791ca8aea617?w=600&q=80"
            nameLower.contains("chicken") || nameLower.contains("poultry") ->
                "https://images.unsplash.com/photo-1587593810167-a84920ea0781?w=600&q=80"
            nameLower.contains("fish") || nameLower.contains("tilapia") ->
                "https://images.unsplash.com/photo-1519708227418-c8fd9a32b7a2?w=600&q=80"
            nameLower.contains("bread") || nameLower.contains("bakery") ->
                "https://images.unsplash.com/photo-1509440159596-0249088772ff?w=600&q=80"
            nameLower.contains("spice") || nameLower.contains("masala") ->
                "https://images.unsplash.com/photo-1596040033229-a9821ebd058d?w=600&q=80"

            // Category fallbacks
            catLower.contains("veg") ->
                "https://images.unsplash.com/photo-1566385101042-1a0aa0c1268c?w=600&q=80"
            catLower.contains("fruit") ->
                "https://images.unsplash.com/photo-1619566636858-adf3ef46400b?w=600&q=80"
            catLower.contains("tuber") ->
                "https://images.unsplash.com/photo-1518977676601-b53f82aba655?w=600&q=80"
            catLower.contains("cereal") || catLower.contains("pulse") ->
                "https://images.unsplash.com/photo-1586201375761-83865001e31c?w=600&q=80"
            catLower.contains("meat") ->
                "https://images.unsplash.com/photo-1603048588665-791ca8aea617?w=600&q=80"
            catLower.contains("dairy") ->
                "https://images.unsplash.com/photo-1563636619-e9143da7973b?w=600&q=80"

            else ->
                "https://images.unsplash.com/photo-1542838132-92c53300491e?w=600&q=80"
        }
    }
}
