
# temporary-component.api ClojureScript namespace

##### [README](../../../README.md) > [DOCUMENTATION](../../COVER.md) > temporary-component.api

### Index

- [append-component!](#append-component)

- [remove-component!](#remove-component)

### append-component!

```
@param (component) component
@param (function)(opt) callback-f
```

```
@usage W/O callback
(defn my-component [])
(append-component! [my-component])
```

```
@usage W/ callback
(defn my-button [] [:a {:href "foo/bar"}])
(defn click-my-button! [] ...)
(append-component! [my-button] click-my-button!)
```

<details>
<summary>Source code</summary>

```
(defn append-component!
  [component & [callback-f]]
  (remove-container!)
  (create-container!)
  (if-let [temporary-container (dom/get-element-by-id "temporary-container")]
          (let [component-element (-> component hiccup-converter/to-html dom/parse-html)]
               (dom/append-element! temporary-container component-element)
               (if callback-f (callback-f)))))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [temporary-component.api :refer [append-component!]]))

(temporary-component.api/append-component! ...)
(append-component!                         ...)
```

</details>

---

### remove-component!

```
@usage
(remove-component!)
```

<details>
<summary>Source code</summary>

```
(defn remove-component!
  []
  (remove-container!))
```

</details>

<details>
<summary>Require</summary>

```
(ns my-namespace (:require [temporary-component.api :refer [remove-component!]]))

(temporary-component.api/remove-component!)
(remove-component!)
```

</details>

---

This documentation is generated with the [clj-docs-generator](https://github.com/bithandshake/clj-docs-generator) engine.

