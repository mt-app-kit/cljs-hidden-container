
(ns temporary-component.api
    (:require [temporary-component.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (temporary-component.side-effects)
(def append-component! side-effects/append-component!)
(def remove-component! side-effects/remove-component!)
