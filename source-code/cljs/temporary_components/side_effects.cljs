
(ns hidden-container.side-effects
    (:require [reagent.dom.server]
              [dom.api :as dom]
              [time.api :as time]
              [hidden-container.env :as env]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn create-container!
  ; @ignore
  ;
  ; @description
  ; Appends a container (to the DOM tree) for a specific temporary component.
  ;
  ; @param (keyword) container-id
  ;
  ; @usage
  ; (create-container! :my-container)
  ;
  ; @return (DOM-element)
  [container-id]
  (let [body-element (dom/get-body-element) temporary-container (dom/create-element! "div")]
       (dom/set-element-attribute! temporary-container "data-container-name" "temporary-container")
       (dom/set-element-attribute! temporary-container "data-container-id"   (name container-id))
       (dom/set-element-style!     temporary-container {:display :none})
       (dom/append-element!        body-element temporary-container)))

(defn remove-container!
  ; @ignore
  ;
  ; @description
  ; Removes the container (from the DOM tree) of a specific temporary component.
  ;
  ; @param (keyword) container-id
  ;
  ; @usage
  ; (remove-container! :my-container)
  ;
  ; @return (DOM-element)
  [container-id]
  (if-let [temporary-container (env/get-container container-id)]
          (let [body-element (dom/get-body-element)]
               (dom/remove-child! body-element temporary-container))))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn remove-component!
  ; @description
  ; Removes a specific temporary component from the DOM tree.
  ;
  ; @param (keyword) component-id
  ;
  ; @usage
  ; (remove-component! :my-component)
  [component-id]
  (remove-container! component-id))

(defn append-component!
  ; @description
  ; - Parses the given Reagent component into DOM Element object, then appends it to the DOM tree.
  ; - Applies the given 'callback-f' function (if provided) when the component is appended.
  ; - Automatically removes the appended component when its lifetime is elapsed in case the '{:auto-remove? true}' setting is provided.
  ;
  ; @param (keyword) component-id
  ; @param (component) component
  ; @param (map)(opt) options
  ; {:auto-remove? (boolean)(opt)
  ;   Default: false
  ;  :callback-f (function)(opt)
  ;  :lifetime (ms)(opt)
  ;   Default: 1000}
  ;
  ; @usage
  ; (defn my-anchor        [] [:a {:id :my-anchor :href "/my-uri"}])
  ; (defn click-my-anchor! [] (-> (.getElementById js/document "my-anchor") .click))
  ;
  ; (append-component! [my-anchor] click-my-anchor!)
  ([component-id component]
   (append-component! component-id component {}))

  ([component-id component {:keys [auto-remove? callback-f lifetime] :or {lifetime 1000}}]
   (remove-container! component-id)
   (create-container! component-id)
   (if-let [temporary-container (env/get-container component-id)]
           (let [component-element (-> component reagent.dom.server/render-to-string dom/parse-html)]
                (dom/append-element! temporary-container component-element)
                (if callback-f   (callback-f))
                (if auto-remove? (time/set-timeout! (fn [] (remove-component! component-id)) lifetime))))))
