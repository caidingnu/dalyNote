const state = {
    token: '11111',
    tokenExpire: 0,
    stuName:''
};


const mutations = {
    setToken(state: { token: string }, payload: string) {
        state.token = payload || '';
    },
    setTokenExpire(state: { tokenExpire: any }, payload: any) {
        state.tokenExpire = payload || 0;
    },
};

const actions = {
    updateAuthData({commit}: any, payload: { token: string; expire: any }) {
        commit('setToken', payload.token);
        commit('setTokenExpire', payload.expire);
    },
    cleanAuthData({commit}: any) {
        commit('setToken', '666');
        commit('setTokenExpire', 0);
    },
};

export default {
    // namespaced: true,
    state,
    mutations,
    actions,
};
