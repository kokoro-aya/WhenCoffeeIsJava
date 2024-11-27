# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2024.11.27

A first version, yet simple but should have all core features. I hope this will mark a good beginning
for this small library.

- Added: Added conditional matches (if-guard) and simple conditions, by now, this matcher should be
able to represent most common cases of the Kotlin `when` expression
- Changed: removed previous value-returning variant, marked the side-effect matcher as deprecated

## [0.9.0] - 2024.11.19

- Added: Added more primitives like `isAmong` and literal-`is` for our matcher
- Added: build a simple visitor pattern to use a pattern-tree structure for our library's core logic

## [0.0.1] - 2024.11.11

- Added: Implemented a value-returning variant and a literal-testing variant of this `when` class
- Added: Implemented a simple `when` as a "better switch-case" to check against types