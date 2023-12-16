
# cljs-temporary-component

### Overview

The <strong>cljs-temporary-component</strong> is a simple ClojureScript tool
which parses a Reagent component to a DOM element object and appends it to the DOM-tree
within a hidden container then applies a callback function. Useful to append disposable
functional elements such as hidden buttons, hidden inputs, etc.

### Documentation

The <strong>cljs-temporary-component</strong> functional documentation is [available here](https://bithandshake.github.io/cljs-temporary-component).

### deps.edn

```
{:deps {bithandshake/cljs-temporary-component {:git/url "https://github.com/bithandshake/cljs-temporary-component"
                                               :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/cljs-temporary-component/tree/release).

### Documentation

The <strong>cljs-temporary-component</strong> functional documentation is [available here](https://bithandshake.github.io/cljs-temporary-component).

### Changelog

You can track the changes of the <strong>cljs-temporary-component</strong> library [here](CHANGES.md).

# Usage

> Some parameters of the following functions and some further functions are not discussed in this file.
  To learn more about the available functionality, check out the [functional documentation](documentation/COVER.md)!

### Index

- [How to append a Reagent component?](#how-to-append-a-reagent-component)

- [How to remove the appended component?](#how-to-remove-the-appended-component)

### How to append a Reagent component?

The [`temporary-component.api/append-component!`](documentation/cljc/temporary-component/API.md/#append-component)
function parses the passed Reagent component to a DOM element object and appends
it to the DOM-tree within a hidden container.
Only one component could be appended at a time, this function always removes the
previously appended component.

```
(defn my-component []
  [:div "My component"])

(append-component! [my-component])
```

As its second (optional) parameter the `append-component!` function takes a callback
function that will be called after the component appended.

```
(defn my-button
  []
  [:a {:id :my-button :href "/my-link"}])

(defn click-my-button!
  []
  (.click (.getElementById js/document "my-button")))

(append-component! [my-button] click-my-button!)
```

```
(defn my-input
  []
  [:input {:id :my-input :value "My value"}])

(defn print-my-value!
  []
  (println (.-value (.getElementById js/document "my-input"))))

(append-component! [my-input] print-my-value!)
```

### How to remove the appended component?

The [`temporary-component.api/remove-component!`](documentation/cljc/temporary-component/API.md/#remove-component)
function removes the last appended component from the DOM-tree.

```
(remove-component!)
```
