import {Action, Module, Mutation, VuexModule} from 'vuex-module-decorators';

@Module
export default class Stu extends VuexModule {
    // state
    common: object | any = {
        stuName: "初始化名称",
    };

    // getter
    get getStuName() {
        return this.common.stuName;
    }

    @Mutation
    public SET_STUNAME(name: string) {
        this.common.stuName = name;
    }

    @Action
    public setStuName(name: string) {
        this.common.stuName = name
    }

}



