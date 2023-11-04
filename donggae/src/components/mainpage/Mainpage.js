import styles from "./Mainpage.module.css"
import { useNavigate } from "react-router-dom";
import Header from "./Header";


export default function Home() {
    const history = useNavigate();
    return (
        <div className={styles.default}>
            <div className={styles.inner}>

            <Header />
            
            </div>
        </div>

    );
}