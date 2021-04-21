import React, {useState} from 'react';

const LoginForm = () => {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    return(
       <React.Fragment>
          <h2>Have an Account?</h2>
          <h4>Login here</h4>
          <br />

          <form noValidate>
              <div className="form-group">
                 <label htmlFor="email">Email Address</label>
                 <input noValidate id="email"
                     type="email"
                     name="email"
                     placeholder="Email"
                     value={email}
                     onChange={(e)=>setEmail(e.target.value)}
                     className="form-control" />
              </div>
              <div className="form-group">
                 <label htmlFor="password">Password</label>
                 <input noValidate id="password"
                     type="password"
                     name="password"
                     placeholder="Password"
                     value={password}
                     onChange={(e)=>setPassword(e.target.value)}
                     className="form-control" />
              </div>
              <button type="submit" className="btn btn-primary mr-2">
                 Login | <i className="fas fa-sign-in-alt"></i>
              </button>
               <button className="btn btn-outline-secondary">
                  Cancel | <i className="fas fa-times"></i>
               </button>
          </form>
       </React.Fragment>
    );
};

export default LoginForm;