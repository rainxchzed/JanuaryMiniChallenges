package zed.rainxch.januaryminichallenges.recipe.presentation.model

enum class Recipe(
    val title: String,
    val ingredients: String,
    val description: String,
) {
    CREAMY_MUSHROOM_SOUP(
        "Creamy Mushroom Soup",
        "Mushrooms, vegetable broth, onion, garlic, cream, thyme",
        "Sauté onions and garlic until fragrant, then add mushrooms and broth. Simmer gently and finish with cream for a smooth, comforting soup."
    ),
    WINTER_VEGETABLE_STEW(
        "Winter Vegetable Stew",
        "Carrots, potatoes, parsnips, onion, vegetable broth, rosemary",
        "Cook chopped vegetables with herbs in vegetable broth until tender. Serve hot as a hearty winter stew."
    ),
    SPICED_LENTIL_SOUP(
        "Spiced Lentil Soup",
        "Red lentils, vegetable broth, carrot, onion, garlic, cumin",
        "Sauté the chopped vegetables with garlic and spices until fragrant, then add lentils and broth. Simmer until the lentils soften and the soup thickens."
    ),
    POTATO_LEEK_COMFORT_SOUP(
        "Potato & Leek Comfort Soup",
        "Potatoes, leeks, butter, vegetable broth, cream",
        "Slowly cook leeks in butter, add potatoes and broth, then simmer until soft. Blend lightly and finish with cream."
    ),
    GINGER_HONEY_TEA(
        "Ginger Honey Tea",
        "Fresh ginger, honey, lemon, water",
        "Steep sliced ginger in hot water, then add honey and lemon. Serve warm for a soothing winter drink."
    ),
    HOT_SPICED_COCOA(
        "Hot Spiced Cocoa",
        "Cocoa powder, milk, cinnamon, nutmeg, sugar",
        "Heat milk with cocoa and spices until smooth and rich. Serve warm with a light sprinkle of cinnamon."
    ),
    APPLE_CINNAMON_BREW(
        "Apple Cinnamon Brew",
        "Apple slices, cinnamon sticks, cloves, honey, water",
        "Simmer apples and spices in water to release their flavor. Sweeten lightly and serve warm."
    ),
    WARM_BANANA_OAT_MUFFINS(
        "Warm Banana Oat Muffins",
        "Bananas, oats, flour, eggs, honey, baking powder",
        "Mix mashed bananas with oats and batter ingredients, then bake until golden and soft."
    ),
    CINNAMON_SWIRL_ROLLS(
        "Cinnamon Swirl Rolls",
        "Flour, cinnamon, butter, sugar, yeast",
        "Roll soft dough with cinnamon sugar filling and bake until fluffy. Serve warm for best flavor."
    ),
    BAKED_APPLE_CRISP(
        "Baked Apple Crisp",
        "Apples, oats, butter, brown sugar, cinnamon",
        "Bake sliced apples topped with a crunchy oat mixture until golden and bubbling."
    )
}