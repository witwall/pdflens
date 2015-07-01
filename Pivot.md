# Introduction  copy from Apache PDFBox maillist #
```

For those unfamiliar, aside from being a very nice widget library, Pivot
follows a 'XUL' style of development model.   That is, you declaratively
define your UI with an xml markup language (WTKX) and then you just feed
that document to a renderer, which creates the Java object hierarchy based
on the document.  So long as the object classes are visible to the renderer,
it can create them.  The primary focus here is for UI objects, but in
reality most any simple Java object graph can be described and generated
this way.  The same xml markup can be used to deploy an application into
different contexts (web, desktop, mobile device) simply by having different
rendered implementations appropriate to the context.

I don't want to dictate what the exact development model should be since
others will probably kick this off.  But Pivot is very easy to get an
application started and has a very clean development and deployment 'story'.


Plus it is generally a good thing to leverage other Apache projects.  See
```
http://pivot.apache.org/index.html

and:

http://www.ibm.com/developerworks/xml/tutorials/x-pivottut/index.html


for more info.