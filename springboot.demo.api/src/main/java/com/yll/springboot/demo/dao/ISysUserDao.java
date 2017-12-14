package com.yll.springboot.demo.dao;

import com.yll.springboot.demo.vo.SysUserVO;

public interface ISysUserDao{

    /** <一句话功能简述>
     * <功能详细描述>
     * @param nickname
     * @param pswd
     * @return [参数说明]
     * 
     * @return SysUserVO [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    SysUserVO selectByNameAndPwd(String nickname, String pswd);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param user [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    void update(SysUserVO user);

}