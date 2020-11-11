#### EditTextView：取消焦点&自动获取焦点

> 一直都遇到这个问题，总是忘记，记录一下
>
> 摘抄一下这个博客：[https://www.cnblogs.com/yongdaimi/p/10606262.html](!https://www.cnblogs.com/yongdaimi/p/10606262.html]) 

* 取消焦点

  找到EditTextView的父控件，并设置如下即可：

  ```
  android:focusable="true"
  android:focusableInTouchMode="true"
  ```

* 自动获取焦点

  ```
  et_text.setFocusable(true);
  et_text.setFocusableInTouchMode(true);
  InputMethodManager inputManager =(InputMethodManager)et_text.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
  inputManager.showSoftInput(et_text, 0);
  ```

* 进入activity就获得焦点，弹出键盘

  ```
  et_text = (EditText) findViewById(R.id.et_text);
  et_text.setFocusable(true);
  et_text.setFocusableInTouchMode(true);
  et_text.requestFocus();
  ```

  并在清单文件中设置该Activity的属性： android:windowSoftInputMode="stateVisible" 