# cn1-ikvm-uwp
IKVM fork with UWP support for Codename One

## Building From Source

**Requirements:**

1. Nant
2. Ant
3. JDK8 or higher

**Building The Full IKVM**

This will build all of IKVM including OpenJDK.  Generally try to do this as little as possible as it takes a while to compile.

~~~
$ ant compile-all
~~~

**Building only the IKVM Runtime**

This will only build the IKVM runtime.  You must have previously run `compile-all` for this to work.  Generally run this one if you've only made changes inside the "runtime" directory that you need to compile.

~~~~
$ ant compile-runtime
~~~~

More instructions to come

