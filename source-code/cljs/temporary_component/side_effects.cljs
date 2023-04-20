
(ns temporary-component.side-effects
    (:require [dom.api     :as dom]
              [reagent.api :as reagent]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn create-container!
  ; @ignore
  ;
  ; @return (DOM-element)
  []
  (let [body-element        (dom/get-body-element)
        temporary-container (dom/create-element! "div")]
       (dom/set-element-id!    temporary-container "temporary-element")
       (dom/set-element-style! temporary-container {:display :none})
       (dom/append-element!    environment-element temporary-container)))

(defn remove-container!
  ; @ignore
  ;
  ; @return (DOM-element)
  []
  (if-let [temporary-container (dom/get-element-by-id "temporary-element")]
          (let [body-element (dom/get-body-element)]
               (dom/remove-child! environment-element temporary-container))))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn append-component!
  ; @param (component) component
  ; @param (function)(opt) callback-f
  ;
  ; @usage W/O callback
  ; (defn my-component [])
  ; (append-component! [my-component])
  ;
  ; @usage W/ callback
  ; (defn my-button [] [:a {:href "foo/bar"}])
  ; (defn click-my-button! [] ...)
  ; (append-component! [my-button] click-my-button!)
  ([component]
   (remove-container!)
   (create-container!)
   (let [temporary-container (dom/get-element-by-id "temporary-element")]
        (reagent/render component temporary-container)))

  ([component callback-f]
   (remove-container!)
   (create-container!)
   (let [temporary-container (dom/get-element-by-id "temporary-element")]
        (reagent/render component temporary-container callback-f))))

(defn remove-component!
  ; @usage
  ; (remove-component!)
  []
  (remove-container!))
