
## Issues

Non-existent genre throws exception instead of returning empty
Can't inject book db for testing (could address this by using the 'addBook' function on the library
but this doesn't help the author db situation

getBooksForGenre throws an exception when it shouldn't
getBooksForGenre function doesn't work (book genre comparison is bad)
    - pointless map
    - pointless check
    - string returned nowhere
    - poor variable naming
    - mutability
    - return statement

getBooksForAuthor
    - repeated code from getBooksByGenre
    - author db call  is bad (uses the id get function, but passes name string)
    - same issues from above

getAuthorTotalPages
    - Same as getBooksForAuthor
    - count starts at 1
    - count is added 2 times
    - count uses mutability

addBookToAuthor
    - Uses the wrong function from the db class (should use either getByName or list then filter)
    - throws an exception when it shouldn't (according to tests)


Other general issues:

    - Repeated code
    - Too many 'primitives' string params - introduce classes (extra points for value class / opaque type)
    - addBook can add a book where the author doesn't exist
    - getBook is never used
    - models.Book and models.Author are linked by author name, should be by author id
    - 2 'DB' classes could use a common trait or similar - would require some sort of serialisation
    - Mutability (outside db)
    - All 1 file