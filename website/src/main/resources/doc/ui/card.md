There are five extension methods to build cards. These extension methods simply add a corresponding CSS
class to the element.

* `.card.wrap` -> `card-wrap`
* `.card.header` -> `card-header`
* `.card.body` -> `card-body`
* `.card.footer` -> `card-footer`
* `.card.title` -> `card-title`

You need to have these classes defined in your CSS. 

For example, with TailwindCSS you might define them as follows:

```css
.card-wrap {
    @apply bg-white sm:shadow sm:rounded-lg;
}

.card-header {
    @apply px-4 py-3 sm:px-6 border-b border-gray-200;
}

.card-body {
    @apply p-4 md:px-6 md:rounded-lg;
}

.card-footer {
    @apply px-4 py-3 sm:px-6 border-t border-gray-200;
}

.card-title {
    @apply text-lg md:text-xl font-medium tracking-wide text-gray-700
}
```

[Example](/tailwind/example-card)
