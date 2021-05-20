# nijikan

A Clojure library for evolving a modified game of life logic against a
2-dimensional grid.

## Usage

The functions are exposed from the `nijikan.core` namespace, but the primary
function for evolving a grid over unknown generations is `run`, which returns
a lazy sequence of generations starting with the first.

```
(nijikan/run [[0 0 0]
              [2 0 2]
              [0 0 0]])
```

## License

Copyright © 2021 Michael S. Daines

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
