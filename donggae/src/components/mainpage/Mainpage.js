import styles from "./Mainpage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "./Header";


export default function Home() {
    const history = useNavigate();
    const button_startCustom = () => { history("/custom"); };
    const button_startAuto = () => { history("/auto"); };
    return (
        <div className={styles.default}>
            <div className={styles.inner}>

            <Header />
            
            </div>
        </div>

    );
}