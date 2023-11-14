import AddStudent from "./component/AddStudent";
import Appbar from "./component/Appbar";
import DisplayStudent from "./component/DisplayStudent";
import Footer from "./component/Footer";

function App() {
  return (
    <div className="app">
      <Appbar/>
      <AddStudent/>
      <DisplayStudent/>
      <Footer/>
    </div>
  );
}

export default App;
