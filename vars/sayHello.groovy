#!/usr/bin/env groovy
import GlobalVars

def call(String name = 'human') 
{
  echo "Hello, ${name}."
  println GlobalVars.foo
}
