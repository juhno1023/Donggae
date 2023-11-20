import styles from './TeamCard.module.css';

const TeamCard = ({ name, title, member}) => {

    console.log(member)
    return (
        <> 
            <div className={styles.GroupCard}>
                💙{name}💙
                <div>{title}</div>
                <div>{member.name}</div>
                <div></div>
            </div>
        </>
    );
};

export default TeamCard;