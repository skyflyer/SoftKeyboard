SoftKeyboard
============

Demo for showing soft keyboard on screen

In portrait mode, you can see the whole contents of the view (it is a ScrollView containing LinearLayout). In landscape mode, however, keyboard is "over" the view and cripples it.

One can obtain the visible height though (through getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);)
