package com.example.eshopbackend.controller;

import com.example.eshopbackend.model.CartItem;
import com.example.eshopbackend.model.Item;
import com.example.eshopbackend.model.User;
import com.example.eshopbackend.service.ItemService;
import com.example.eshopbackend.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ItemService itemService;

    public UserController(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    // --- שליפת כל המשתמשים (בשביל רישום, בדיקות וכו') ---
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // --- רישום משתמש חדש ---
    @PostMapping
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("יש להזין כתובת אימייל");
        }
        if (userService.getUserByEmail(user.getEmail()) != null) {
            return ResponseEntity.badRequest().body("האימייל כבר קיים במערכת");
        }
        String password = user.getPassword();
        if (password == null || password.length() < 8 || password.length() > 20 ||
                !password.matches(".*[A-Za-z].*") || !password.matches(".*\\d.*")) {
            return ResponseEntity.badRequest()
                    .body("הסיסמה חייבת להיות בין 8 ל-20 תווים ולכלול גם אותיות וגם מספרים");
        }
        User saved = userService.saveUser(user);
        return ResponseEntity.ok(saved);
    }

    // --- התחברות משתמש ---
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User login) {
        if (login.getEmail() == null || login.getPassword() == null) {
            return ResponseEntity.badRequest().body("יש למלא אימייל וסיסמה");
        }
        User user = userService.getUserByEmailAndPassword(login.getEmail(), login.getPassword());
        if (user == null) {
            return ResponseEntity.status(401).body("אימייל או סיסמה לא נכונים");
        }
        return ResponseEntity.ok(user);
    }

    // --- עגלת קניות ---

    // הוספת פריט לעגלה
    @PostMapping("/{userId}/cart/{itemId}")
    public User addToCart(@PathVariable Long userId, @PathVariable Long itemId, @RequestParam(defaultValue = "1") int quantity) {
        User user = userService.getUserById(userId);
        Item item = itemService.getItem(itemId);
        if (user == null || item == null) return null;

        List<CartItem> cart = user.getCart();

        // בדיקה אם כבר יש את הפריט בעגלה — נעדכן כמות
        boolean found = false;
        for (CartItem ci : cart) {
            if (ci.getItem().getId().equals(itemId)) {
                ci.setQuantity(ci.getQuantity() + quantity);
                found = true;
                break;
            }
        }
        if (!found) {
            cart.add(new CartItem(item, quantity));
        }
        user.setCart(cart);
        return userService.saveUser(user);
    }

    // עדכון כמות של פריט בעגלה
    @PutMapping("/{userId}/cart/{itemId}")
    public User updateCartItem(@PathVariable Long userId, @PathVariable Long itemId, @RequestParam int quantity) {
        User user = userService.getUserById(userId);
        if (user == null) return null;
        List<CartItem> cart = user.getCart();

        for (CartItem ci : cart) {
            if (ci.getItem().getId().equals(itemId)) {
                ci.setQuantity(quantity);
                break;
            }
        }
        user.setCart(cart);
        return userService.saveUser(user);
    }

    // הסרת פריט מהעגלה
    @DeleteMapping("/{userId}/cart/{itemId}")
    public User removeFromCart(@PathVariable Long userId, @PathVariable Long itemId) {
        User user = userService.getUserById(userId);
        if (user == null) return null;
        List<CartItem> cart = user.getCart();

        cart.removeIf(ci -> ci.getItem().getId().equals(itemId));
        user.setCart(cart);
        return userService.saveUser(user);
    }

    // שליפת כל העגלה
    @GetMapping("/{userId}/cart")
    public List<CartItem> getCart(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) return null;
        return user.getCart();
    }

    // --- מועדפים ---

    // שליפת רשימת המועדפים של משתמש
    @GetMapping("/{userId}/favorites")
    public List<Item> getFavorites(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) return null;
        return user.getFavorites();
    }

    // הוספת פריט למועדפים
    @PostMapping("/{userId}/favorites/{itemId}")
    public User addFavorite(@PathVariable Long userId, @PathVariable Long itemId) {
        User user = userService.getUserById(userId);
        Item item = itemService.getItem(itemId);
        if (user == null || item == null) return null;

        // הימנע מהוספת כפולים
        if (!user.getFavorites().contains(item)) {
            user.getFavorites().add(item);
            userService.saveUser(user);
        }
        return user;
    }

    // הסרת פריט מהמועדפים
    @DeleteMapping("/{userId}/favorites/{itemId}")
    public User removeFavorite(@PathVariable Long userId, @PathVariable Long itemId) {
        User user = userService.getUserById(userId);
        Item item = itemService.getItem(itemId);
        if (user == null || item == null) return null;

        user.getFavorites().remove(item);
        return userService.saveUser(user);
    }
}
