# Java Memory Reference

## Compare                                     
| Type | Get reference | GC condition   | Possible Memory Leak   |
| -----------------------| :------------- | :------------- |:------------- |
| Strong Reference   |  Directly  | Never | might be | 
| Soft Reference   |  Use get() | When Memory is critical | Never |
| Weak Reference   |  Use get() | Always | Never |
| Phantom Reference   |  never | never | might be |