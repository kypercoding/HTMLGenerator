# Overview

This project is a small Java script intended to help automate the task of inserting information in HTML templates. The program depends on the use of placeholders, which are variable names encapsulated in some unique pattern in the HTML (i.e. {{{VARIABLE_NAME}}} ) as well as .properties files indicating what to put in place of those variables. This project was originally intended as a tool to generate static HTML files for static websites, conceptually similar to Flask's Jinja2 or Node's EJS format.

## Installation

This project only makes use of built-in Java libraries; as such, there are no extra dependecies necessary. There are two ways to install the application onto your computer:

### Source Code
Clone the repository into a directory of your choice. Once the source code is cloned, move into the cloned directory and perform the following commands to run the app:

> javac App.java

> java App

### ZIP File

Under releases, there is a ZIP file containing a directory called *input* and an executable JAR. After downloading the ZIP file, extract the contents of the ZIP file to a directory of your choice. Upon doing so, please run with the following command:

> java -jar BlogPostGenerator.jar

## Usage

Before running the program, open up the file *input.html* inside the *input* directory in the text editor of your choice. Here, write HTML containing variables encapsulated in triple brackets, like so:

```HTML
<!DOCTYPE html>
<html>
    <head>
        <title>{{{TITLE}}}</title>
    </head>
    <body>
        <h1>{{{TITLE}}}</h1>
        <h1>{{{AUTHOR}}}</h1>
        <h1>{{{DATE}}}</h1>
    </body>
</html>
```

Once this is done, open up the *input.properties* in the *input* directory in the text editor of your choice. Here, write HTML containing each variable and its value, like so:

```
TITLE=My Post title
AUTHOR=FirstName LastName
DATE=YYYY-MM-DD
```

A couple of things to note:
* Please ensure that the variables in the .properties file matches EXACTLY that of the variables in the .html file.
* Please ensure that none of the variables are repeated (i.e. do not add another TITLE=[some value] line into the .properties file).

Now, run the program. It will ask for four things:

* The path to the .properties file (containing each variable and their corresponding values).
* The path to the .html file containing the placeholder variables.
* The path to the .html file in which to write the output.
* The pattern distinguishing a variable, with the characters *%s* inside the pattern.

By default, the program will look for a folder called *input* inside the current working directory, which contains the files *input.properties*, *input.html*, and *output.html*, and use the triple brackets to find and replace variable names inside the file (i.e. **{{{%s}}}** ). With the setup we used above, the *output.html* file should become the following:

```HTML
<!DOCTYPE html>
<html>
    <head>
        <title>My Post Title</title>
    </head>
    <body>
        <h1>My Post Title</h1>
        <h1>FirstName LastName</h1>
        <h1>YYYY-MM-DD</h1>
    </body>
</html>
```

NOTE: any files open in a text editor during the execution of the program may haved to be closed or reloaded for the changes to take effect.
