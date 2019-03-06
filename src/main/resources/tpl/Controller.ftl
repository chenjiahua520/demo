package ${project.controllerPackage};

import com.ziqius.common.base.BaseController;
import com.ziqius.common.model.UpmsUser;
import com.ziqius.common.util.IdGen;
import com.ziqius.common.util.UserUtils;
import com.ziqius.estate.common.Result;
import ${project.modelPackage}.${model};
import ${project.servicePackage}.${service};
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${remarks}Controller
 *
 * @author ${project.author}
 * @version ${project.version}
 */
@Controller
@RequestMapping("/manage/${modelName}")
@Api(value = "${remarks}Controller", description = "${remarks}管理")
public class ${controller} extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(${controller}.class);

    @Resource
    private ${service} ${serviceName};

    /**
     * ${remarks}管理页面
     *
     * @return jsp
     * @author ${project.author}
     * @date ${project.version}
     */
    @ApiOperation(value = "${remarks}管理页面")
    @RequiresPermissions("${project.name}:${modelName}:index")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/manage/${modelName}/index.jsp";
    }

    /**
     * ${remarks}列表
     *
     * @param ${modelName} ${modelName}
     * @param pageNumber  pageNumber
     * @param pageSize    pageSize
     * @return object
     * @author ${project.author}
     * @date ${project.version}
     */
    @ApiOperation(value = "${remarks}列表")
    @RequiresPermissions("${project.name}:${modelName}:list")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(${model} ${modelName}, @RequestParam(defaultValue = "1") int pageNumber, @RequestParam(defaultValue = "10") int pageSize) {
        List<${model}> list = ${serviceName}.selectByExampleForStartPage(example, pageNumber, pageSize);
        int total = ${serviceName}.countByExample(example);
        Map<String, Object> data = new HashMap<String, Object>(2);
        data.put("rows", list);
        data.put("total", total);
        return data;
    }

    /**
     * 新增${remarks}
     *
     * @return jsp
     * @author ${project.author}
     * @date ${project.version}
     */
    @ApiOperation(value = "新增${remarks}")
    @RequiresPermissions("${project.name}:${modelName}:create")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "/manage/${modelName}/create.jsp";
    }

    /**
     * 新增${remarks}
     *
     * @param ${modelName} ${modelName}
     * @return object
     * @author ${project.author}
     * @date ${project.version}
     */
    @ApiOperation(value = "新增${remarks}")
    @RequiresPermissions("${project.name}:${modelName}:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(${model} ${modelName}) {
        UpmsUser user = UserUtils.getUser();
        ${modelName}.setCreateBy(user.getUserId().toString());
        ${modelName}.setCreateDate(new Date());
        ${modelName}.setUpdateBy(user.getUserId().toString());
        ${modelName}.setUpdateDate(new Date());
        ${modelName}.setDelFlag("0");
        ${modelName}.setId(IdGen.uuid());
        int count = ${serviceName}.insert(${modelName});
        if (count > 0) {
            return new Result(HttpStatus.OK, null);
        }
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    /**
     * 修改${remarks}
     *
     * @param ${modelName} type
     * @param model       model
     * @return jsp
     * @author ${project.author}
     * @date ${project.version}
     */
    @ApiOperation(value = "修改${remarks}")
    @RequiresPermissions("${project.name}:${modelName}:update")
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(${model} ${modelName}, Model model) {
        ${model} type = ${serviceName}.selectByPrimaryKey(${modelName}.getId());
        model.addAttribute("${modelName}", type);
        return "/manage/${modelName}/update.jsp";
    }

    /**
     * 修改${remarks}
     *
     * @param ${modelName} ${modelName}
     * @return object
     * @author ${project.author}
     * @date ${project.version}
     */
    @ApiOperation(value = "修改${remarks}")
    @RequiresPermissions("${project.name}:${modelName}:update")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(${model} ${modelName}) {
        UpmsUser user = UserUtils.getUser();
        ${modelName}.setUpdateBy(user.getUserId().toString());
        ${modelName}.setUpdateDate(new Date());
        int count = ${serviceName}.updateByPrimaryKeySelective(${modelName});
        if (count > 0) {
            return new Result(HttpStatus.OK, null);
        }
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

    /**
     * 删除${remarks}
     *
     * @param ids ids
     * @return object
     * @author ${project.author}
     * @date ${project.version}
     */
    @ApiOperation(value = "删除${remarks}")
    @RequiresPermissions("${project.name}:${modelName}:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(String ids) {
        int count = ${serviceName}.deleteBulkLogic(ids);
        if (count > 0) {
            return new Result(HttpStatus.OK, null);
        }
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR, null);
    }

}