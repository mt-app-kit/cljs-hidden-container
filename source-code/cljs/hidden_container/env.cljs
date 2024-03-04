
(ns hidden-container.env
    (:require [dom.api :as dom]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn get-container
  ; @ignore
  ;
  ; @description
  ; Returns the container element of a specific temporary component.
  ;
  ; @param (keyword) container-id
  ;
  ; @usage
  ; (get-container :my-container)
  ; =>
  ; #object[HTMLDivElement]
  ;
  ; @return (DOM-element)
  [container-id]
  (let [container-query (str "[data-container-id="(name container-id)"], [data-container-name=temporary-container]")]
       (dom/get-element-by-query container-query)))
