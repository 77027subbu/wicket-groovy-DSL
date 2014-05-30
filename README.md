#Apache Wicket Groovy DSL Project

## Overview
Apache Wicket is great, and Groovy is also great. This project tries to combine the power of both. However, sometimes Apache Wicket code become damn verbose.
But with some little, yet powerful Groovy DSL written, we can extend Wicket to simplify common tasks, and to delete over 30-40% of verbose code.
Versions used:
#### 1. Apache Wicket 6.15.0
#### 2. Groovy 2.3.2 (Yes, with Traits :)
#### 3. Twitter bootstap for some beautify for examples
#### 4. GORM Standalone module from Grails for examples

#### Operators overloaded:
#####' +  ' => parentComponent.add(child)
#####' -  ' => parentComponent.remove(child)
#####' >> ' => component.replaceWith(another)
#####' << ' => component.addOrReplace(another)

## Pimp my library pattern
This project follows Pipm my library pattern described by Martin Odersky in 2006 year. The Pimp my Library Pattern suggests an approach for extending a library that nearly does everything that you need but just needs a little more. It assumes that you do not have source code for the library of interest.
Read more here: http://groovy.codehaus.org/Pimp+my+Library+Pattern

## Examples
Later I will provide more complex examples. But to understand the concept, I think it is enough.
At this moment Label, AjaxLink, MarkupContainer, Form, StatelessForm, ListView, Link, BookmarkableLink, PasswordField, EmailTextField, UrlTextField, TextField for Numbers, TextField, DropDownChoice with ChoiceRenderer, Fragment, CheckBoxMultipleChoice are implemented

##Usage
```groovy
...
use(WicketDSL, WicketFormDSL) {
    def form = form 'wicketForm', [model: new CompoundPropertyModel(this),
      submit: { println this.input1 + this.input2 },
      visible: { this.input1 != this.input2 }
      error: { println 'Form validation failed'; } ]
    form.text 'input1'
    form.text 'input2'
    form.email 'emailInput'
    form.url 'urlInput'
    form.password 'passwordInput'
    ['one', 'two', 'three'].listView('listView') { ListItem item ->
        item.label 'itemLabel', item.model
    }
}
```
Instead of verbose Java
```java
...
   Form form = new Form("wicketForm", new CompoundPropertyModel(this)) {
        @Override
        public void onSubmit(){
            System.out.println(MyPage.this.input1 + MyPage.this.input2);
        }
        @Override
        public boolean isVisible() {
            // just for example :)
            !MyPage.this.input1.equals(MyPage.this.input2);
        }
        @Override
        public void onError() {
            System.out.println("Form validation failed");
        }
   }
   form.add(new TextField("input1"));
   form.add(new TextField("input2"));
   form.add(new EmailTextField("emailInput"));
   form.add(new UrlTextField("urlInput"));
   form.add(new PasswordTextField("emailInput"));
   add(form);
   ArrayList<String> list = new ArrayList<>();
   list.add("one"); list.add("two"); list.add("three");
   add(new ListView("listView", list) {
        @Override
        protected void populateItem(ListItem<T> item) {
            item.add(new Label("itemLabel", item.getModel()));
        }
   })
```

And this is just a small example of usage. Also with an override map (in this example with 'submit' key), you can pass another properties to components, like
visible, enabled and others (take a look at traits wicket/core/traits package).

##GORM integration
In example folder there is GORM (orm from Grails) integrated with Apache Wicket and this DSL, take a look! It is very interesting!
No special components are required :)
```groovy
use(WicketDSL) {
            def listView = Person.findAll().listView('personList')
            { ListItem<Person> item ->
                item.label 'personName', item.model
                item.label 'department', new PropertyModel(item.model, 'department.title')
            }
            bookmarkLink('addPerson', AddPersonPage)
            this + listView
        }
```
```groovy
use(WicketDSL) {
            def form = form 'person', [model:new CompoundPropertyModel(new Person())
                                       submit: { Person.withTransaction {
                                        it.modelObject.save(flush:true) }
                                       }]
            form.text 'firstName'
            form.text 'lastName'
            form.password 'password'
            form.url 'webSite'
            this + form
        }
```
##Thanks

### - to great Groovy Community, and especially to Andrey Bloschetsov.
### - to Martin Dashorst, for post about this project at http://wicketinaction.com
### - to Martin Grigorov, for providing comments and suggestions, making this project better

##Development
DSL is not complete, it is just the attempt to show you the concept.
Feel free to contact me, to advise me how to make this DSL better.

##Help
I need help in testing this DSL, also I need your opinion on this