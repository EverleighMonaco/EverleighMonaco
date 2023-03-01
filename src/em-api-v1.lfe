(defmodule em-api-v1
  (export all))

(include-lib "_build/default/lib/lfest/include/lfest-routes.lfe")


(defun get-data
  "If you'd like to see the the arg parameter printed out for any given
  dispatch below, simply comment it out. The lines that print to stdout
  are commented below in order to better assess perfmance when benchmarking."
  ('GET
   #"/hello"
   (lfest-resp:content
    "application/json"
    (jsx:encode(example-listings)))))

(defun routes
  "REST API Routes"
  (('() method arg-data)
    (get-data method arg-data))
  ; XXX add more routes here for your application
  ;(((list "another" "path") method arg-data)
  ; (your-app:your-func method arg-data))
  ;
  ; When nothing matches, do this
  ((path method arg)
    ; (logjam:error
    ;   "Unmatched route!~nPath-info: ~p~nmethod: ~p~narg-data: ~p~n~n"
    ;   (list path method arg))
    #(content
      "application/json"
      "{\"error\": \"Unmatched route.\"}")))

(defun out (arg-data)
  "This is called by YAWS when the requested URL matches the URL specified in
  the YAWS config (see ./etc/yaws.conf) with the 'appmods' directive for the
  virtual host in question.

  In particular, this function is intended to handle all v1 traffic for this
  REST API."
  (em-util:meta-out arg-data #'routes/3))

(clj:defn example-listings []
     (map
      'schema #"everleigh-demo" ; look at https://github.com/ibpdi/cdm
                                ; and https://github.com/Yodata/yodata and pick something plz
      'listing_agent (map
                      'schema #"everleigh-demo" ; temp, leave here and below for now
                      'name #"Estelle (Merrell) L. Hales"
                      'brokerage #"Merrel, Walker, and Evereigh Co."
                      'photo (list
                              'schema #"everleigh-demo"
                              'image_url #"/images/stock/StockSnap_O2KAZO8QFM-1080p.jpg"
                              'attrib_url #"https://stocksnap.io/author/directmedia"
                              'attrib_name #"Direct Media"))
      'listings
          (list
           (map 'address #"1156 Runnymede Rd"
                'city #"Toronto"
                'state #"Ontario"
                'postal_code #"M6S 2Z7"
                'country #"Canada"
                'phone #"555-555-5555" 
                'what #"For sale by owner"
                'loot #"4000000"
                'dom 1
                'photos (list
                         (map 'attrib_url #"https://stocksnap.io/author/32212"
                              'attrib_name #"Binyamin Mellish"
                              'meta #"a big family sized house. Number 1156"
                              'image_url #"/images/stock/StockSnap_LJ515CPAKI-1080p.jpg"
                              'car 'true)
                         (map 'attrib_url #"https://stocksnap.io/author/dahlhousedesign"
                              'attrib_name #"Dahl House Design"
                              'meta #"A wide photo bedroom with a bed, a fireplace, a flat screen, and a view."
                              'image_url #"/images/stock/StockSnap_KWRZNZ6DC6-1080p"
                              'cdr 'true)
                          (map 'attrib_url #"https://stocksnap.io/author/dahlhousedesign"
                              'attrib_name #"Dahl House Design"
                              'meta #"Welcome!"
                              'image_url #"/images/stock/StockSnap_GTTRWBKT3H-1080p.jpg"
                              'cdr 'true)                             
                          (map 'attrib_url #"https://stocksnap.io/author/dahlhousedesign"
                              'attrib_name #"Dahl House Design"
                              'meta #"Welcome!"
                              'image_url #"/images/stock/StockSnap_D0NOMANBA7-1080p.jpg"
                              'cdr 'true)))
           (map 'address #"4971 Mountain Avenue"
                'city #"Toronto"
                'state #"Ontario"
                'postal_code #"R6M 1Y2"
                'country #"Canada"
                'phone #"555-555-5556")
           (map 'address #"1442 Queens Sq"
                'city #"Toronto"
                'state #"Ontario"
                'postal_code #"N3C 1H3"
                'country #"Canada"
                'phone #"555-555-5556")
           (map 'address #"1221 Rue King"
                'city #"Toronto"
                'state #"Ontario"
                'postal_code #"J1H 1R4"
                'country #"Canada"
                'phone #"555-555-5556"))))

