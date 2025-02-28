import axiosRequest from 'axios';

export const axios = axiosRequest.create({
  timeout: 3 * 60 * 1000,
  baseURL: import.meta.env.VITE_API_URL,
  headers: {
    Accept: 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': '*',
    'Access-Control-Allow-Headers': '*',
    'Content-Type': 'application/json'
  }
});


const isObject = (a) => {
  if ((!!a) && (a.constructor === Object)) {
    return true;
  }
  return false;
};
const _st = (z, g) => {
  return "" + (g != "" ? "[" : "") + z + (g != "" ? "]" : "");
};
const buildURLQueryFromObject = (params, skipObjects, prefix) => {
  if (skipObjects === void 0) {
    skipObjects = false;
  }
  if (prefix === void 0) {
    prefix = "";
  }
  var result = "";
  if (typeof (params) != "object") {
    return prefix + "=" + encodeURIComponent(params) + "&";
  }
  for (var param in params) {
    var c = "" + prefix + _st(param, prefix);
    if (isObject(params[param]) && !skipObjects) {
      result += buildURLQueryFromObject(params[param], false, "" + c);
    } else if (Array.isArray(params[param]) && !skipObjects) {
      params[param].forEach(function (item, ind) {
        result += buildURLQueryFromObject(item, false, c + "[" + ind + "]");
      });
    } else {
      result += c + "=" + encodeURIComponent(params[param]) + "&";
    }
  }
  return result;
};

const handleResponse = (resolve, reject, response) => {
  
  // if (response?.data?.status == 200) {
    resolve(response);
  // } else {
  //   reject(response.data);
  // }
};

const handleError = (reject, error) => {
  // error = Object.assign(error, {
  //   status: 500,
  //   message: 'Có lỗi kết nối với máy chủ hệ thống',
  // });

  reject(error);
};

export const sendGet = (url, payload = null) => {
  return new Promise((resolve, reject) => {
    const queryParamString = payload ? `?${buildURLQueryFromObject(payload)}` : '';
    axios.get(`${url}${queryParamString}`)
      .then((response) => {
        handleResponse(resolve, reject, response);
      })
      .catch((error) => {
        handleError(reject, error);
      });
  });
};

export const sendPost = (url, payload) => {
  return new Promise((resolve, reject) => {
    axios.post(url, payload)
      .then((response) => {
        handleResponse(resolve, reject, response);
      })
      .catch((error) => {
        handleError(reject, error);
      });
  });
};

export const sendPut = (url, payload, config) => {
  return new Promise((resolve, reject) => {
    axios.put(url, payload, config)
      .then((response) => {
        handleResponse(resolve, reject, response);
      })
      .catch((error) => {
        handleError(reject, error);
      });
  });
};

export const sendPatch = (url, payload) => {
  return new Promise((resolve, reject) => {
    axios.patch(url, payload)
      .then((response) => {
        handleResponse(resolve, reject, response);
      })
      .catch((error) => {
        handleError(reject, error);
      });
  });
};

export const sendDelete = (url, payload) => {
  return new Promise((resolve, reject) => {
    const queryParamString = payload ? `?${new URLSearchParams(payload).toString()}` : '';

    axios.delete(`${url}${queryParamString}`)
      .then((response) => {
        handleResponse(resolve, reject, response);
      })
      .catch((error) => {
        handleError(reject, error);
      });
  });
};


