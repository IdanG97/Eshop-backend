package com.example.eshopbackend;

import com.example.eshopbackend.model.Item;
import com.example.eshopbackend.service.ItemService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EShopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EShopBackendApplication.class, args);
	}

	@Bean
	public org.springframework.boot.CommandLineRunner seedItems(ItemService itemService) {
		return args -> {
			if (itemService.getAllItems().isEmpty()) {

				// טלוויזיות
				itemService.saveItem(new Item(
						"Samsung 55\" 4K TV",
						"טלוויזיה חכמה, 55 אינץ', 4K",
						"/images/samsung-55-4k.jpg", // שם קובץ התמונה ששמרת
						2999.99, 5));
				itemService.saveItem(new Item(
						"LG 65\" OLED TV",
						"טלוויזיית OLED חכמה, 65 אינץ'",
						"/images/lg-65-oled.jpg",
						5699.00, 3));
				itemService.saveItem(new Item(
						"TCL 43\" FHD TV",
						"טלוויזיה FHD חכמה, 43 אינץ'",
						"/images/tcl-43-fhd.jpg",
						1199.00, 7));

				// טלפונים
				itemService.saveItem(new Item(
						"iPhone 15 Pro",
						"אייפון 15 פרו 256GB",
						"/images/iphone-15-pro.jpg",
						4600.00, 8));
				itemService.saveItem(new Item(
						"Samsung Galaxy S24 Ultra",
						"סמסונג גלקסי S24 Ultra 256GB",
						"/images/samsung-s24-ultra.jpg",
						3800.00, 10));
				itemService.saveItem(new Item(
						"Xiaomi Redmi Note 13",
						"שיאומי רדמי נוט 13",
						"/images/xiaomi-redmi-note-13.jpg",
						890.00, 15));

				// מחשבים
				itemService.saveItem(new Item(
						"MacBook Air M2",
						"מחשב נייד אפל, 13 אינץ'",
						"/images/macbook-air-m2.jpg",
						4900.00, 3));
				itemService.saveItem(new Item(
						"Dell XPS 15",
						"דל XPS, מסך 15.6 אינץ', i7",
						"/images/dell-xps-15.jpg",
						6399.00, 2));
				itemService.saveItem(new Item(
						"Lenovo Legion Tower",
						"מחשב גיימינג נייח",
						"/images/lenovo-legion-tower-7i.jpg",
						4499.00, 2));

				// מוצרי מטבח
				itemService.saveItem(new Item(
						"KitchenAid Mixer",
						"מיקסר מקצועי למטבח",
						"/images/KitchenAid-Mixer.jpg",
						1300.00, 4));
				itemService.saveItem(new Item(
						"קומקום חשמלי Gold Line",
						"קומקום חשמלי 1.7 ליטר",
						"/images/Electric-kettle-Gold-Line.jpg",
						119.00, 13));
				itemService.saveItem(new Item(
						"Bosch Blender",
						"בלנדר עוצמתי Bosch",
						"/images/Bosch-Blender.jpg",
						349.00, 6));
				itemService.saveItem(new Item(
						"Morphy Richards Grill",
						"טוסטר לחיצה 2000W",
						"/images/Morphy-Richards-Grill.jpg",
						229.90, 7));
				itemService.saveItem(new Item(
						"DeLonghi Espresso Machine",
						"מכונת אספרסו מקצועית",
						"/images/DeLonghi-Espresso-Machine.jpg",
						1800.00, 2));
				itemService.saveItem(new Item(
						"Samsung Microwave",
						"מיקרוגל דיגיטלי 28 ליטר",
						"/images/Samsung-Microwave-28L.jpg",
						399.00, 8));
			}
		};
	}
};