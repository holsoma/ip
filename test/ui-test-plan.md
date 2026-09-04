# UI test plan

The UI tests exercise Ted through its standard input and check each response
before continuing to the next command. The latest console transcript is stored
in [ui-test-session.txt](ui-test-session.txt).

## Task types and status

Aim: verify that todos, deadlines, and events are created with their type
icons and details, remain in one polymorphic list, and can be marked done.

Commands and expected output fragments:

1. todo borrow book -> [T][ ] borrow book
2. deadline return book /by Sunday -> [D][ ] return book (by: Sunday)
3. event project meeting /from Mon 2pm /to 4pm ->
   [E][ ] project meeting (from: Mon 2pm to: 4pm)
4. mark 2 -> [D][X] return book (by: Sunday)
5. list -> 1.[T][ ] borrow book, 2.[D][X] return book (by: Sunday),
   and 3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
6. deadline do homework /by no idea :-p ->
   [D][ ] do homework (by: no idea :-p)
7. list -> 4.[D][ ] do homework (by: no idea :-p)
8. bye -> Bye. Hope to see you again soon!

## Invalid input

Aim: verify incomplete deadline and event commands are rejected without
adding tasks.

1. deadline missing date ->
   OOPS!!! Use: deadline DESCRIPTION /by DATE_OR_TIME
2. event missing end /from Monday ->
   OOPS!!! Use: event DESCRIPTION /from START /to END

## General command errors

Aim: verify empty todo descriptions and unknown commands are reported without
adding tasks or ending the session.

1. todo -> OOPS!!! The description of a todo cannot be empty.
2. blah -> OOPS!!! I'm sorry, but I don't know what that means :-(
3. mark abc -> OOPS!!! Please provide a valid task number.
4. mark 9 -> OOPS!!! That task number is not in the list.
5. list -> 4.[D][ ] do homework (by: no idea :-p)
6. bye -> Bye. Hope to see you again soon!
