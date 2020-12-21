

# doxia-module-html

**doxia-module-html** allows you to use HTML as a source for your maven-generated site documentation

HTML being a file format which has been somehow overlooked by the maven steering committee in favour of more common documentation file formats such as LaT<sub>E</sub>X, iText, FML, FO, RTF, markdown and TWiki. 

## What about XHTML ?

OK, yes, there is an XHTML Doxia module. 

The advantage that **doxia-module-html** has over **doxia-module-xhtml** is that it lets you use non-conformant (i.e. real) HTML docs without having to jump through the hoops required to get the '[Validated XHTML 1.0](https://en.wikipedia.org/wiki/File:Valid_XHTML_1.0.svg)' tick that everyone definitely still puts on their website in 2020.

You know, this one. 

<img src="https://upload.wikimedia.org/wikipedia/commons/1/1f/Valid_XHTML_1.0.svg" width="120">

Admittedly, you're still restricted to the subset of HTML that the maven developers decided to support in doxia, but that's still a hell of a lot better than the alternatives, IMO.

## Overview

It uses doxia-module-xhtml, but runs it through [tagsoup](http://vrici.lojban.org/~cowan/XML/tagsoup/) first. 


## How do I use it ? 

Add it to the 'dependencies' list of your **maven-site-plugin** (i.e. **not** the dependencies of your project): 
```
<build>
  <pluginManagement>
    <plugins>
      <plugin>
	    <groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-site-plugin</artifactId>
		<version>3.3</version>
		<dependencies>
		  <dependency>
			<groupId>com.randomnoun.maven.doxia</groupId>
			<artifactId>doxia-module-html</artifactId>
			<version>1.1.0</version>
		  </dependency>
		</dependencies>
	  </plugin>
	</plugins>
  </pluginManagement>
</build>
```

## Licensing

doxia-module-html is licensed under the BSD 2-clause license.