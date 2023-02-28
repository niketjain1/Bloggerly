import CustomNavbar from "./CustomNavbar";

const Base =({title = "Welcome to our websites", children}) =>{
    return (
        <div className="container-fluid p-0 m-0">
            <CustomNavbar />
            
            { children }

            <h1>This is a header</h1>
        </div>
    )
}

export default Base;