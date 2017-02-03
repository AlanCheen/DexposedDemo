/*
 * Copyright (C) 2016, 程序亦非猿
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.yifeiyuan.dexposeddemo;

import android.app.Application;
import android.widget.Toast;

import com.taobao.android.dexposed.DexposedBridge;

/**
 * Created by 程序亦非猿 on 17/1/23.
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

       boolean enable = DexposedBridge.canDexposed(this);

        Toast.makeText(this, "enable?:"+enable, Toast.LENGTH_SHORT).show();

        if (enable) {

        }

    }
}
