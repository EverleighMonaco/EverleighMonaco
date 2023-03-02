;; All our example data for the next lil phase of development.
;; Here we'll start to build our plumbing, pipes and taps for 
;; our data.


(defmodule em-data
  (export all))

(include-lib "lfe/include/clj.lfe")

(defn listings []
  "An example listing"
     (map
      #"schema" #"everleigh-demo" ; look at https://github.com/ibpdi/cdm
                                ; and https://github.com/Yodata/yodata and pick something plz
      #"meta" #"example-listings"
      #"listings"
          (list
           (listing-1)
           (listing-2)
           (listing-3)
           (listing-4)
           )))

(defn agent []
  "An example agent"
    (map
      #"id" #"769f49a5-aff6-4527-b4eb-92f7849acee3"
      #"meta" #"listing-agent"
      #"schema" #"everleigh-demo" ; temp, leave here and below for now
      #"name" #"Estelle (Merrell) L. Hales"
      #"brokerage" #"Merrel, Walker, and Evereigh Co."
      #"photo" (agent-photo)))

(defn agent-photo []
  "An example agent photo"
    (map
      #"id" #"6c38179a-85dd-433e-a6ab-b2c0f394672c"
      #"meta" #"agent-photo"
      #"schema" #"everleigh-demo"
      #"image_url" #"/images/stock/StockSnap_O2KAZO8QFM-1080p.jpg"
      #"attrib_url" #"https://stocksnap.io/author/directmedia"
      #"attrib_name" #"Direct Media"))

(defn listing-1 []
  "An example listing"
    (map
      #"id" #"64f9d5f7-a788-4fbc-aa78-d9ad6a8f6e66"
      #"listing-agent" (agent)
      #"meta" #"listing"
      #"address" #"1156 Runnymede Rd"
      #"city" #"Toronto"
      #"state" #"Ontario"
      #"postal_code" #"M6S 2Z7"
      #"country" #"Canada"
      #"phone" #"555-555-5555" 
      #"what" #"For sale by owner"
      #"loot" 100
      #"loot-insturment" #"BTC"
      #"dom" 1
      #"photos" (list
                 (map #"attrib_url" #"https://stocksnap.io/author/32212"
                      #"attrib_name" #"Binyamin Mellish"
                      #"desc" #"a big family sized house. Number 1156"
                      #"image_url" #"/images/stock/StockSnap_LJ515CPAKI-1080p.jpg"
                      #"id" #"9840bc88-5712-4f41-b943-cf83b4e0ee17"
                      #"car" 'true)
                 (map #"attrib_url" #"https://stocksnap.io/author/dahlhousedesign"
                      #"attrib_name" #"Dahl House Design"
                      #"meta" #"A wide photo bedroom with a bed, a fireplace, a flat screen, and a view."
                      #"image_url" #"/images/stock/StockSnap_KWRZNZ6DC6-1080p"
                      #"id" #"ecf84ca1-c4d2-4235-9922-1355438b1c67"
                      #"cdr" 'true)
                 (map #"attrib_url" #"https://stocksnap.io/author/dahlhousedesign"
                      #"attrib_name" #"Dahl House Design"
                      #"meta" #"Welcome!"
                      #"image_url" #"/images/stock/StockSnap_GTTRWBKT3H-1080p.jpg"
                      #"id" #"c18fe921-5664-404f-9d4e-ac3446baf783"
                      #"cdr" 'true)                             
                 (map #"attrib_url" #"https://stocksnap.io/author/dahlhousedesign"
                      #"attrib_name" #"Dahl House Design"
                      #"meta" #"Welcome!"
                      #"image_url" #"/images/stock/StockSnap_D0NOMANBA7-1080p.jpg"
                      #"id" #"9a5971dd-a433-44e0-8f89-8239abaf7cf7"
                      #"cdr" 'true))))

(defn listing-2 []
  "An example listing"
    (map
       #"id" #"ca13a279-ec31-43e8-a2a0-db32818ecaba"
       #"listing-agent" (agent)
       #"meta" #"listing"
       #"address" #"4971 Mountain Avenue"
       #"city" #"Toronto"
       #"state" #"Ontario"
       #"postal_code" #"R6M 1Y2"
       #"country" #"Canada"
       #"phone" #"555-555-5555" 
       #"what" #"For sale by owner"
       #"loot" 2000000
       #"loot-insturment" #"CAD"
       #"dom" 15
       #"photos" (list
                  (map #"attrib_url" #"https://stocksnap.io/author/42333"
                       #"attrib_name" #"Expect Best"
                       #"desc" #"This place is from the future."
                       #"image_url" #"/images/stock/StockSnap_CQKQCT0EUW-1080p.jpg"
                       #"id" #"a7a6c9b1-bcbe-45a2-888b-f95cbc450308"
                       #"car" 'true))))

(defn listing-3 []
  "An example listing"
    (map
       #"id" #"60d35f47-8fb0-4abe-922b-2a8ceefbb086"
       #"listing-agent" (agent)
       #"meta" #"listing"
       #"address" #"22 Queens Sq West"
       #"city" #"Toronto"
       #"state" #"Ontario"
       #"postal_code" #"N3C 1H3"
       #"country" #"Canada"
       #"phone" #"555-555-5555" 
       #"what" #"For sale"
       #"loot" 80000000
       #"loot-insturment" #"CAD"
       #"dom" 3 
       #"photos" (list
                  (map #"attrib_url" #"https://stocksnap.io/author/43541"
                       #"attrib_name" #"Daniel Frank"
                       #"desc" #"She's a classic."
                       #"image_url" #"/images/stock/StockSnap_GRJ0A7Q0NQ-1080p.jpg"
                       #"id" #"c059f7f2-44bc-42c2-a8c5-f9785b67f039"
                       #"car" 'true))))


(defn listing-4 []
  "An example listing"
    (map
       #"id" #"a18b1bb1-e2a2-41a3-b18b-dc879704d833"
       #"listing-agent" (agent)
       #"meta" #"listing"
       #"address" #"1221 Rue King"
       #"city" #"Toronto"
       #"state" #"Ontario"
       #"postal_code" #"J1H 1R4"
       #"country" #"Canada"
       #"phone" #"555-555-5555" 
       #"what" #"For sale"
       #"loot" 200
       #"loot-insturment" #"BTC"
       #"dom" 3 
       #"photos" (list
                  (map #"attrib_url" #"https://stocksnap.io/author/34904"
                       #"attrib_name" #"Jesse Roberts"
                       #"desc" #"BREATHTAKING."
                       #"image_url" #"/images/stock/StockSnap_IZTO6FB5HM-1080p.jpg"
                       #"id" #"a3b4a1c1-412e-43ee-84ed-a07643525dc0"
                       #"car" 'true))))
