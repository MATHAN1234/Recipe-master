package com.example.recipe_app.Model;

    public class Fooddata {

        private String username;
        private String itemImage;
        private String itemName;
        private String itemCategory;
        private String iteming1;
        private String iteming2;
        private String iteming3;
        private String iteming4;
        private String step1;
        private String step2;
        private String step3;
        private String step4;
        private String key;


        public Fooddata(){}




        public Fooddata( String username, String itemImage, String itemName, String itemCategory, String iteming1, String iteming2, String iteming3, String iteming4, String step1, String step2, String step3, String step4) {
            this.username = username;
            this.itemImage = itemImage;
            this.itemName = itemName;
            this.itemCategory = itemCategory;
            this.iteming1 = iteming1;
            this.iteming2 = iteming2;
            this.iteming3 = iteming3;
            this.iteming4 = iteming4;
            this.step1 = step1;
            this.step2 = step2;
            this.step3 = step3;
            this.step4 = step4;
        }


        public String getUsername() { return username; }

        public String getItemImage() {
            return itemImage;
        }

        public String getItemName() {
            return itemName;
        }

        public String getItemCategory() {
            return itemCategory;
        }

        public String getIteming1() {
            return iteming1;
        }

        public String getIteming2() {
            return iteming2;
        }

        public String getIteming3() {
            return iteming3;
        }

        public String getIteming4() {
            return iteming4;
        }

        public String getStep1() {
            return step1;
        }

        public String getStep2() {
            return step2;
        }

        public String getStep3() {
            return step3;
        }

        public String getStep4() {
            return step4;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
