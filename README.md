
# cljs-temporary-component

### Overview

The <strong>cljs-temporary-component</strong> is a simple ClojureScript tool that
appends the passed Reagent component to the React-tree into a hidden container
then applies a callback function. Useful to append one-use functional elements
such as hidden buttons, hidden inputs, etc.

### Documentation

The <strong>cljs-temporary-component</strong> functional documentation is [available here](documentation/COVER.md).

### deps.edn

```
{:deps {bithandshake/cljs-temporary-component {:git/url "https://github.com/bithandshake/cljs-temporary-component"
                                               :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/cljs-temporary-component/tree/release).

### Documentation

The <strong>cljs-temporary-component</strong> functional documentation is [available here](documentation/COVER.md).

### Changelog

You can track the changes of the <strong>cljs-temporary-component</strong> library [here](CHANGES.md).

### Index

- [How to append a Reagent component?](#how-to-append-a-reagent-component)

- [How to remove the appended component?](#how-to-remove-the-appended-component)

# Usage

### How append a Reagent component?

The [`temporary-component.api/append-component!`](documentation/cljc/temporary-component/API.md/#append-component)
function appends the passed Reagent component to the React-tree into a hidden container.
Only one component could be appended at a time, this function always removes the
previously appended component.

```
(defn my-component []
  [:div "My component"])

(append-component! [my-component])
```

As its third (optional) parameter the `append-component!` function takes a callback
function that will be called after the component rendered.

```
(defn my-button
  []
  [:a#my-button {:href "/my-link"}])

(defn click-my-button!
  []
  (.click (.getElementById js/document "my-button")))

(append-component! [my-button] click-my-button!)
```

### How remove the appended component?

The [`temporary-component.api/remove-component!`](documentation/cljc/temporary-component/API.md/#remove-component)
function removes the last appended component from the React-tree.

```
(remove-component!)
```
