
(ns temporary-components.side-effects
    (:require [dom.api              :as dom]
              [hiccup-converter.api :as hiccup-converter]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn create-container!
  ; @ignore
  ;
  ; @return (DOM-element)
  []
  (let [body-element        (dom/get-body-element)
        temporary-container (dom/create-element! "div")]
       (dom/set-element-id!    temporary-container "temporary-container")
       (dom/set-element-style! temporary-container {:display :none})
       (dom/append-element!    body-element temporary-container)))

(defn remove-container!
  ; @ignore
  ;
  ; @return (DOM-element)
  []
  (if-let [temporary-container (dom/get-element-by-id "temporary-container")]
          (let [body-element (dom/get-body-element)]
               (dom/remove-child! body-element temporary-container))))

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
  [component & [callback-f]]
  (remove-container!)
  (create-container!)
  (if-let [temporary-container (dom/get-element-by-id "temporary-container")]
          (let [component-element (-> component hiccup-converter/to-html dom/parse-html)]
               (dom/append-element! temporary-container component-element)
               (if callback-f (callback-f)))))

(defn remove-component!
  ; @usage
  ; (remove-component!)
  []
  (remove-container!))
