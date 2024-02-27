
(ns temporary-components.api
    (:require [temporary-components.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (temporary-components.side-effects)
(def append-component! side-effects/append-component!)
(def remove-component! side-effects/remove-component!)


; component-id + ne csak egyet lehessen, hanem külön container-eket adjon a dom-hoz
