# Revisions

> This was done under time constraints that may have forced you down
> a path or pattern that ended up with some code that is non ideal. Are there
> any sections that you would have done differently with hindsight? Anything
> that you'd like to refactor given more time?

## Cell Rules

When implementing the first test case, my initial answer differed. This was
due to "exactly 2 adults". I interpreted this as two adults and nothing else,
but presence of newborn and seniors do not affect it.

The main difficulty was in wrangling the output on failure, so having a
pretty-print built in to the testing response could have made this quicker,
but only if we had a need to pretty print a lot more.

## Replacing Everything

The simplest approach I settled on what replacing each cell with each
generation. Given that I knew the scope of 20 generations from the outset,
this didn't worry me.

Given a chance to re-implement, I might use an approach where I pass back a
modification function from `age` so that it would only update fields where
necessary.
