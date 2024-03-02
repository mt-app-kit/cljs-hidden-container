
(ns hidden-container.api
    (:require [hidden-container.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (hidden-container.side-effects/*)
(def append-component! side-effects/append-component!)
(def remove-component! side-effects/remove-component!)
