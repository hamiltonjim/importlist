Import List
===========

Personal finance manager [Moneydance®](http://www.moneydance.com) includes a [feature to import transaction files](http://moneydance.com/userguide-contents/importing%20additional%20information%20into%20moneydance.html), such as from Quicken™. Using third-party background applications to download transaction files on a regular basis I felt that Moneydance® lacks an overview of which files still have to be imported, which, in turn, forces the user to constantly check on the file system.

As a result, this extension monitors a given directory and displays all of its transaction files in a sortable list inside the homepage view along with two corresponding buttons to import and to remove each transaction file.


Installing the extension
------------------------

### Requirements
*	Java Runtime Environment, version 6
*	Moneydance®, tested with version 2010r3 and 2011-preview

### Instructions
1.	Download the [latest signed version of Import List](http://moneydance.com/download/modules/importlist.mxt) from the official extensions repository.
2.	Add the downloaded extension file to Moneydance® by choosing **Add…** from the **Extensions** menu.
3.	You will be asked to choose a base directory to monitor (if it's a first-time setup). In case you want to change the base directory later, you can select **Import List** from the **Extensions** menu.
4.	In order to display Import List in Moneydance®'s homepage view, open the **Preferences** window and click on the **Home Page** tab. The available items should contain an entry called **Import List**. Add it to the left or right column of your homepage view.


Building the extension from scratch
-----------------------------------

### Requirements
*	Java Development Kit, version 6
*	Apache Ant™, tested with version 1.8.2

### Instructions
1.	`git clone git@github.com:my-flow/importlist.git` creates a copy of the repository.
2.	`ant genkeys` generates a passphrase-protected key pair.
3.	`ant importlist` compiles and signs the extension.
4.	`dist/importlist.mxt` is the resulting extension file.

### Further Assistance
Consult the Core API and the developer's kit, both of which are part of the [Moneydance® Developer Resources](http://www.moneydance.com/developer).