/*
 * Created by LuaView.
 * Copyright (c) 2017, Alibaba Group. All rights reserved.
 *
 * This source code is licensed under the MIT.
 * For the full copyright and license information,please view the LICENSE file in the root directory of this source tree.
 */

package com.iqyi.paopao.dynamicuisdk.vm.extend;

import com.iqyi.paopao.dynamicuisdk.util.LogUtil;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

/**
 * Base Lib extend for LuaJ
 * @author song
 * @date 16/2/22
 */
public class BaseLib {
    org.luaj.vm2.lib.BaseLib baseLib;
    Globals globals;

    public BaseLib(org.luaj.vm2.lib.BaseLib baseLib, Globals globals) {
        this.baseLib = baseLib;
        this.globals = globals;
    }

    public void extend(LuaValue env) {
        env.set("printLV", new printLV(baseLib));
    }

    // "print", // (...) -> void
    final class printLV extends VarArgFunction {
        final org.luaj.vm2.lib.BaseLib baselib;

        printLV(org.luaj.vm2.lib.BaseLib baselib) {
            this.baselib = baselib;
        }

        public Varargs invoke(Varargs args) {
            StringBuffer sb = new StringBuffer();
            for (int i = 1, n = args.narg(); i <= n; i++) {
                if (i > 1) {
                    sb.append('\t');
                }


            }
            LogUtil.i(sb);
            return NONE;
        }
    }
}
